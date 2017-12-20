(ns netcost.content
    (:require [reagent.core :as reagent :refer [atom]]))

(def ls (atom nil))

(def empty-table
  (vec (repeat 4 (vec (range 15)))))

(defn -item [i y width]
  [:div.item {:style {:min-width (str width "px")}} (str i y)])

(defn -row [i x state]
  [:div.row
    (for [j (range (count x))
          :let [y (nth x j) width (nth state j)]]
    ^{:key (str i y)} [-item i y width])])

(defn did-mount [this state]
  (reset! ls state))

(defn create-table [state]
   [:div.content
     (for [i (range (count empty-table))
           :let [x (nth empty-table i)]]
        ^{:key (str "x" i)} [-row i x state])])

(defn -content [state]
  [create-table state])

(defn content [state]
  (fn []
    (reagent/create-class
         {:component-did-mount #(println "content did mount")
          :component-will-update #(println "content will update")
          :component-did-update #(println "content did update")
          :reagent-render -content})))


