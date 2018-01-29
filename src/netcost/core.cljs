(ns netcost.core
    (:require [reagent.core :as reagent :refer [atom]]
              [netcost.filter-block :refer [filter-block]]
              [netcost.info-block :refer [info-block]]
              [netcost.content-block :refer [content-block]]))

(enable-console-print!)

(defn app [] [:div.netcost-app
              [filter-block]
              [info-block]
              [content-block]])

(reagent/render-component
  [app] (. js/document (getElementById "mount")))

(defn on-js-reload [])
