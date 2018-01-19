(ns netcost.body
    (:require [reagent.core :as reagent :refer [atom]]
              [netcost.content :refer [content]]
              [netcost.side :as side]
              [netcost.util :as util]))

(enable-console-print!)


(defn body [state]
  (reagent/create-class
   {:reagent-render
    (fn [state]
      (let [height (str "calc(100% - " (:scroll-height-margin @state) "px)")]
        (prn height)
        [:div.grid-body {:style {:max-height height}} 
         [side/fixed state]
         [content state]]))}))
