(ns netcost.head
    (:require [reagent.core :as r]))

(enable-console-print!)

(defn -item [props]
  [:div.head-top-item
        [:div.item-content props]])


(defn- -fixed-head-top []
  [:div.head-top-content
    (for [item (range 200)]
      ^{:key item} [-item "xxxx xxxx xxxx"])])


(defn did-mount [this state]
  (swap! state assoc :head-el (r/dom-node this)))

(defn get-size [el]
  (let [children (.from js/Array (.-children el))]
    (mapv #(.-width (.getBoundingClientRect %)) children)))

(defn fixed-head-top [state]
  (r/create-class
    {:component-did-mount #(did-mount % state)
     :reagent-render (fn [] (-fixed-head-top))}))

(defn corner [state]
  (r/create-class
   {:component-did-update #(println "corenr up")
    :reagent-render
    (fn [state]
      [:div.corner
        {:style {:min-width (str (:left-head-width @state) "px")}}])}))

(defn head [state]
  [:div.head-top [corner state] [fixed-head-top state]])
