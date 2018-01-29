(ns netcost.content
    (:require [reagent.core :as reagent :refer [atom]]))

(defn -item [data-item width]
  [:div.item {:style {:min-width (str width "px")}} data-item])

(defn -row [row-data state]
  [:div.row
    (for [j (range (count row-data))
            :let [data-item (nth row-data j) width (nth state j)]]
        ^{:key (random-uuid)} [-item data-item width])])

(defn on-scroll [target head-el side-el]
  (set! (.-scrollLeft head-el) (.-scrollLeft target))
  (set! (.-scrollTop side-el) (.-scrollTop target)))

(defn did-mount [this state]
  (swap! state assoc :content-node (reagent/dom-node this)))

(defn create-table [state data]
  (reagent/create-class
   {:component-did-mount #(did-mount % state)
    :component-will-update #(println "create table will update")
    :component-did-update #(println "create table did update")
    :reagent-render
    (fn [state data]
      (let [top-head-width (:columns-width @state)
            data-table (:content data)]
        [:div.content
         (for [i (range (count data-table))
               :let [row-data (nth data-table i)]]
           ^{:key (random-uuid)} [-row row-data top-head-width])]))}))

(defn -content [state data]
  (fn [] (println "content render") [create-table state data]))

(defn content [state data]
  (when-not (empty? (:columns-width @state))
    (reagent/create-class
        {:component-did-mount #(println "content did mount")
         :component-will-update #(println "content will update")
         :component-did-update #(println "content did update")
         :reagent-render -content})))

