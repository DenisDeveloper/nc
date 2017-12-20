(ns netcost.util)

(defn get-size [el]
  (let [children (.from js/Array (.-children el))]
    (mapv #(.-width (.getBoundingClientRect %)) children)))
