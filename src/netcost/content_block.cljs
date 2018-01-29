(ns netcost.content-block
  (:require [reagent.core :as reagent]
            [netcost.grid :refer [grid]]))

(def head-row (into [] (range 200)))
(def head-column (into [] (range 200)))

(def grid-data {:head-row (range 20)
                :head-column (vec (range 2))
                :content (repeat 2 (repeat 20 " "))})

(def foo (reagent/atom grid-data))

;; (defn get-grid-data []
;;   (let [head-row (vec (range 20))
;;         head-coll (vec (range 20))]))

;; (time (map (fn [x] (map #("x") (range 200))) (range 200)))

(defn set-data [json]
  (let [d (js->clj json :keywordize-keys true)
        head-row (map :name (:data d))
        head-column (map :name (:items d))]
    (swap! foo assoc
           :head-row head-row
           :head-column head-column)))

(-> (js/fetch "http://selfcost.math.bia-tech.ru/services/items")
    (.then #(.json %))
    (.then set-data))

(defn content-block []
  (reagent/create-class {:component-did-mount #(prn "content-block did mount")
                         :reagent-render (fn [] [:div.content-block [grid @foo]])}))
