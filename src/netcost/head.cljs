(ns netcost.head
  (:require [reagent.core :as r]
            [clojure.string :as s]
            [netcost.util :as util]))

(enable-console-print!)


(defn -item [props]
  [:div.head-top-item
        [:div.item-content props]])


(defn s1 [n]
  (s/join (repeat n "*")))

(defn rand-str [n1 n2]
  (str (s/join " "
               (repeatedly (rand-int n1)
                           #(s1 (rand-int n2))))
       "***"))

(defn- -fixed-head []
  [:div.head-top-content
    (for [item (range 200)]
      ^{:key item} [-item (rand-str 4 6)])])

(defn did-mount [this state]
  (swap! state assoc :head-node (r/dom-node this)))

(defn fixed-head [state]
  (r/create-class
    {:component-did-mount #(did-mount % state)
     :reagent-render (fn [] (-fixed-head))}))

(defn corner [state]
  (r/create-class
   {:component-did-update #(println "corenr up")
    :reagent-render
    (fn [state]
      (prn "corner render")
      [:div.corner
        {:style {:min-width (str (:first-column-width @state) "px")}}])}))

(defn head [state]
  [:div.head-top [corner state] [fixed-head state]])
