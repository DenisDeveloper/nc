(ns netcost.util)

(defn get-size [el]
  (let [children (.from js/Array (.-children el))]
    (mapv #(.-width (.getBoundingClientRect %)) children)))

(defn re-calc [state])

(def a {:a 1 :b 2 :c 3 :d 4})
(def b {:a 4 :c 1 :d 4 :e "fff" :h {:dd 123}})

(defn foo [m1 m2]
  (merge m1 m2))

(foo a b)

