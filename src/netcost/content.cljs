(ns netcost.content
    (:require [reagent.core :as reagent :refer [atom]]))

(def empty-table
  (vec (repeat 200 (vec (range 200)))))

(defn -item [i y width]
  [:div.item {:style {:min-width (str width "px")}} (str i y)])

(defn -row [i x state]
  [:div.row
    (for [j (range (count x))
            :let [y (nth x j) width (nth state j)]]
        ^{:key (str i y)} [-item i y width])])

(defn on-scroll [target head-el side-el]
  (set! (.-scrollLeft head-el) (.-scrollLeft target))
  (set! (.-scrollTop side-el) (.-scrollTop target)))

(defn create-table [state]
  (let [top-head-width (:columns-width @state)]
   [:div.content {:on-scroll #(on-scroll (.-target %) (:head-el @state) (:side-el @state))}
     (for [i (range (count empty-table))
           :let [x (nth empty-table i)]]
       ^{:key (str "x" i)} [-row i x top-head-width])]))

(defn -content [state]
  (fn [] (println "content render") [create-table state]))

(defn content [state]
  (when-not (empty? (:columns-width @state))
    (reagent/create-class
        {:component-did-mount #(println "content did mount")
         :component-will-update #(println "content will update")
         :component-did-update #(println "content did update")
         :reagent-render -content})))


