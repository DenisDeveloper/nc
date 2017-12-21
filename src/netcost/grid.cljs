(ns netcost.grid
    (:require [reagent.core :as reagent :refer [atom]]
              [netcost.head :refer [head]]
              [netcost.body :refer [body]]))

(enable-console-print!)

(def state (atom {:left-head-width 0 :top-head-width [] :head-el nil :side-el nil}))

(defn grid []
  [:div.grid [head state] [body state]])
