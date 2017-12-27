(ns netcost.grid
    (:require [reagent.core :as reagent :refer [atom]]
              [netcost.head :refer [head]]
              [netcost.scroll :as s]
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

(def const-state {:margin (get-size m)})

(def state (atom {:first-column-width 0
                  :columns-width []
                  :head-dom-node nil
                  :side-dom-node nil
                  :size-with-margin nil}))

;; (defn grid []
;;   [:div.grid-wrapper
;;     [:div.grid [head state]
;;                [body state]]
;;     [:div.scroll-bar-y [:div {:style {:height 5800}}]]])


;; (defn grid-wrap [state const-state]
;;   (reagent/create-class
;;    {:component-did-mount #(swap! state assoc :sh (.-scrollHeight (:side-el @state)) :sw (.-scrollWidth (:head-el @state)))
;;     :component-did-update #(println "gw updated")
;;     :reagent-render
;;     (fn [state]
;;       [:div.grid-wrapper
;;             [:div.grid {:style {:width (:margin const-state) :height (:margin const-state)}} [head state] [body state]]
;;             ])}))


(defn grid-wrap [state const-state]
  (reagent/create-class
   {:component-did-update #(println "gw updated")
    :reagent-render
    (fn [state]
      [:div.grid-wrapper
            [:div.grid {:style {:width (:margin const-state) :height (:margin const-state)}} [head state] [body state]]
            ])}))

(defn grid []
  (reagent/create-class
     {:component-did-update #(println "grid updated")
      :reagent-render
      (fn [] [grid-wrap state const-state])}))
