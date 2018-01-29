(ns netcost.side
  (:require [reagent.core :as reagent]))

(enable-console-print!)


(defn did-mount [this state]
  (let [el (reagent/dom-node this)]
    (swap! state assoc :side-node el)))


(defn item [item-data]
  [:div.head-left-item
   (when (= 1 1) [:div.expand [:i {:aria-hidden "true" :class "fa fa-fw fa-chevron-right"}]])
   [:div.item-content item-data]])


(defn- -fixed [state data]
  (let [head-column (:head-column data)]
    [:div.head-left
     (for [item-data head-column]
      ^{:key (random-uuid)} [item item-data])]))

(defn fixed [state data]
  (reagent/create-class
    {:component-did-mount #(did-mount % state)
     :component-did-update #(println "head did update")
     :reagent-render -fixed}))
