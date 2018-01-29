(ns netcost.head
  (:require [reagent.core :as r]
            [clojure.string :as s]
            [netcost.util :as util]))

(enable-console-print!)


(defn -item [props]
  [:div.head-top-item
        [:div.item-content props]])

(defn- -fixed-head [data]
  (let [head-row (:head-row data)]
    [:div.head-top-content
     (for [item head-row]
      ^{:key item} [-item item])]))

(defn did-mount [this state]
  (swap! state assoc :head-node (r/dom-node this)))

(defn fixed-head [state data]
  (r/create-class
    {:component-did-mount #(did-mount % state)
     :reagent-render (fn [_ data] [-fixed-head data])}))

(defn corner [state]
  (r/create-class
   {:component-did-update #(println "corenr up")
    :reagent-render
    (fn [state]
      (prn "corner render")
      [:div.corner
        {:style {:min-width (str (:first-column-width @state) "px")}}])}))

(defn head [state data]
  [:div.head-top [corner state] [fixed-head state data]])
