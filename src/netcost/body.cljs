(ns netcost.body
    (:require [reagent.core :as reagent :refer [atom]]
              [netcost.content :refer [content]]
              [netcost.side :as side]
              [netcost.util :as util]))

(enable-console-print!)



;; (defn did-update [this state]
;;   (let [el (reagent/dom-node this)
;;         size (.-width (.getBoundingClientRect el))]
;;     (swap! state assoc :left-head-width size)))

;; (defn body-did-update [state]
;;   (let [size (util/get-size (reagent/dom-node (:head-el @state)))]
;;     (swap! state assoc :top-head-width size)))

;; (defn body [state]
;;   (reagent/create-class
;;    {:component-did-update #(body-did-update state)
;;     :reagent-render
;;     (fn [state]
;;       [:div.grid-body
;;         [side/fixed state]
;;         [content state]])}))

(defn body [state]
  (reagent/create-class
   {:reagent-render
    (fn [state]
      (println "body render")
      [:div.grid-body
        [side/fixed state]
        [content state]])}))
