(ns netcost.util)

(defn get-size [el]
  (let [children (.from js/Array (.-children el))]
    (mapv #(.-width (.getBoundingClientRect %)) children)))

(defn get-size-one [el]
  (.-width (.getBoundingClientRect el)))

(defn re-calc [state]
   )


