(ns netcost.info-block
  (:require [reagent.core :as reagent :refer [atom]]))

(defn info-block []
  (reagent/create-class {:component-did-mount #(prn "info-block did mount")
                         :reagent-render (fn [] [:div.info-block])}))
