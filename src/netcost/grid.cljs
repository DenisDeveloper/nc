(ns netcost.grid
    (:require [reagent.core :as reagent :refer [atom]]
              [netcost.head :refer [head]]
              [netcost.scroll :as s]
              [netcost.util :as util]
              [netcost.body :refer [body]]))

(enable-console-print!)


(def m
  (let [scroll-div (.createElement js/document "div")]
    (set! (.-className scroll-div) "scrollbar-measure")
    (.appendChild (.-body js/document) scroll-div)
    (let [width (- (.-offsetWidth scroll-div) (.-clientWidth scroll-div))]
      (.removeChild (.-body js/document) scroll-div) width)))

(defn get-size [margin]
  (str "calc(100% - " margin "px)"))

(def state (atom {:first-column-width 0
                  :columns-width []
                  :head-node nil
                  :side-node nil
                  :content-node nil
                  :scroll-height 0
                  :scroll-width 0
                  :margin nil
                  :scroll-width-margin 0
                  :scroll-height-margin 0
                  :size-with-margin nil}))

(defn scroll-x [el1 el2 target]
  (set! (.-scrollLeft el1) (.-scrollLeft target))
  (set! (.-scrollLeft el2) (.-scrollLeft target)))

(defn scroll-y [el1 el2 target]
  (set! (.-scrollTop el1) (.-scrollTop target))
  (set! (.-scrollTop el2) (.-scrollTop target)))

(defn grid-wrap [state data]
  (reagent/create-class
   {:component-did-update #(println "grid-wrap updated")
    :component-did-mount #(println "grid-wrap mount")
    :reagent-render
    (fn [state data]
      (let [scroll-offset-width (+ (:scroll-width @state) (:scroll-width-margin @state))
            scroll-offset-height (+ (:scroll-height @state) (:scroll-height-margin @state))]
        (prn (:scroll-height-margin @state))
        [:div.grid-wrapper
         [:div.grid
          {:style {:max-width (:margin @state) :max-height (:margin @state)}}
          [head state data] [body state data]]
         [:div.scroll-bar-y
          {:style {:max-height (:margin @state)}
           :on-scroll #(scroll-y (:side-node @state) (:content-node @state) (.-target %))}
          [:div {:style {:width 0.5 :height scroll-offset-height}}]]
         [:div.scroll-bar-x
          {:style {:max-width (:margin @state)}
           :on-scroll #(scroll-x (:head-node @state) (:content-node @state) (.-target %))}
          [:div {:style {:width scroll-offset-width :height 0.5}}]]]))}))

(defn invalidate [state]
  (let [first-col-width (util/get-size-one (:side-node @state))
        scroll-width (.-scrollWidth (:head-node @state))
        scroll-height (.-scrollHeight (:side-node @state))
        cols-width (util/get-size (:head-node @state))
        scroll-width-margin first-col-width
        margin (get-size m)
        scroll-height-margin (.-height (.getBoundingClientRect (:head-node @state)))]
    (swap! state assoc
           :margin margin
           :scroll-height scroll-height
           :scroll-width scroll-width
           :scroll-width-margin scroll-width-margin
           :scroll-height-margin scroll-height-margin
           :first-column-width first-col-width
           :columns-width cols-width)))

; (:columns-width @state)
; (:first-column-width @state)
; (= (:columns-width @state) (util/get-size (:head-node @state)))

; (when-not (and (= 2 2) (= 1 1)) (prn "t"))

(defn grid [data]
  (reagent/create-class
     {:component-did-update (fn []
                              (let [old (:columns-width @state)
                                    new (util/get-size (:head-node @state))
                                    old1 (:first-column-width @state)
                                    new1 (util/get-size-one (:side-node @state))]
                               (when-not (and (= old new) (= old1 new1)) (invalidate state))))
      :component-did-mount #(invalidate state)
      :reagent-render
      (fn [data]
        [grid-wrap state data])}))
