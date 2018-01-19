(ns netcost.core
    (:require [reagent.core :as reagent :refer [atom]]
              [netcost.grid :refer [grid]]))

(enable-console-print!)

(defn app [] [grid])

(def head-row (into [] (range 200)))
(def head-column (into [] (range 200)))
;; (def table-data [])

(reagent/render-component
  [app] (. js/document (getElementById "app")))

(defn on-js-reload [])
