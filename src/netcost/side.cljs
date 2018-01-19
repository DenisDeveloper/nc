(ns netcost.side
  (:require [reagent.core :as reagent]))

(enable-console-print!)


(defn did-mount [this state]
  (let [el (reagent/dom-node this)]
    (swap! state assoc :side-node el)))

(defn- -fixed [state]
  [:div.head-left
    (for [item (range 200)]
      ^{:key item}
        [:div.head-left-item
          [:div.item-content ">>>> >>>> >>>>"]])])

(defn fixed [state]
  (reagent/create-class
    {:component-did-mount #(did-mount % state)
     :component-did-update #(println "head did update")
     :reagent-render -fixed}))    
