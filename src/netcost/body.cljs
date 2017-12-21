(ns netcost.body
    (:require [reagent.core :as reagent :refer [atom]]
              [netcost.content :refer [content]]
              [netcost.util :as util]))

(enable-console-print!)


(def left-head-width (atom 0))

(defn- -fixed-head-left [state]
  [:div.head-left
    (for [item (range 200)]
      ^{:key item}
        [:div.head-left-item
          [:div.item-content ">>>> >>>> >>>>"]])])

(defn did-mount [this state]
  (let [el (reagent/dom-node this)
        size (.-width (.getBoundingClientRect el))]
    (swap! state assoc :left-head-width size :side-el el)))

;; (defn did-update [this state]
;;   (let [el (reagent/dom-node this)
;;         size (.-width (.getBoundingClientRect el))]
;;     (swap! state assoc :left-head-width size)))

(defn fixed-head-left [state]
  (reagent/create-class
    {:component-did-mount #(did-mount % state)
     :component-did-update #(println "head did update")
     :reagent-render -fixed-head-left}))    

(defn body-did-update [state]
  (let [size (util/get-size (reagent/dom-node (:head-el @state)))]
    (swap! state assoc :top-head-width size)))

(defn body [state]
  (reagent/create-class
   {:component-did-update #(body-did-update state)
    :reagent-render
    (fn [state]
      [:div.grid-body [fixed-head-left state]
        (when-not
            (empty? (:top-head-width @state))
            [content state])])}))
