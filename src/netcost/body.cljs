(ns netcost.body
    (:require [reagent.core :as reagent :refer [atom]]
              [netcost.content :refer [content]]
              [netcost.side :as side]
              [netcost.util :as util]))

(enable-console-print!)


(defn body [state data]
  (reagent/create-class
   {:reagent-render
    (fn [state data]
      (let [height (str "calc(100% - " (:scroll-height-margin @state) "px)")]
        [:div.grid-body {:style {:max-height height}} 
         [side/fixed state data]
         [content state data]]))}))
