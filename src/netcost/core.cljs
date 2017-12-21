(ns netcost.core
    (:require [reagent.core :as reagent :refer [atom]]
              [netcost.grid :refer [grid]]))

(enable-console-print!)

;; (defonce app-state (atom {:text "Hello world!"}))

(defn app [] [grid])

(reagent/render-component
  [app] (. js/document (getElementById "app")))

(defn on-js-reload [])
