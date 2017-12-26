(ns netcost.scroll
  (:require [reagent.core :as reagent]))

(defn -x [cstate state]
  [:div.scroll-bar-x
   {:style {:max-width (:margin cstate)}}
   [:div {:style {:height 0.5 :width (:sw state)}}]])


(defn x [cstate state]
  (reagent/create-class
   {:reagent-render (fn [] (println "render x") (-x cstate @state))}))

(defn y [cstate state]
  (reagent/create-class
   {:component-did-update #(println "y updatetd")
    :reagent-render
    (fn [cstate state]
      [:div.scroll-bar-y
        {:style {:max-height (:margin cstate)}}
        [:div {:style {:width 0.5 :height (:scroll-height @state)}}]])}))


