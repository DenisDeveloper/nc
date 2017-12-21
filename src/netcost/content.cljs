(ns netcost.content
    (:require [reagent.core :as reagent :refer [atom]]))

(def ls (atom nil))

(def empty-table
  (vec (repeat 200 (vec (range 200)))))

(defn -item [i y width]
  [:div.item {:style {:min-width (str width "px")}} (str i y)])

(defn -row [i x state]
  [:div.row
    (for [j (range (count x))
            :let [y (nth x j) width (nth state j)]]
        ^{:key (str i y)} [-item i y width])])

(defn did-mount [this state]
  (reset! ls state))

(defn on-scroll [target head-el side-el]
  (set! (.-scrollLeft head-el) (.-scrollLeft target))
  (set! (.-scrollTop side-el) (.-scrollTop target)))

(defn create-table [state]
  (let [top-head-width (:top-head-width @state)]
   [:div.content {:on-scroll (fn [this] (reagent/next-tick #(on-scroll (.-target this) (:head-el @state) (:side-el @state))))}
     (for [i (range (count empty-table))
           :let [x (nth empty-table i)]]
       ^{:key (str "x" i)} [-row i x top-head-width])]))

(defn -content [state]
  [create-table state])

(defn content [state]
  (reagent/create-class
         {:component-did-mount #(println "content did mount")
          :component-will-update #(println "content will update")
          :component-did-update #(println "content did update")
          :reagent-render -content}))


