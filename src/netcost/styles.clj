(ns netcost.styles
  (:require [garden.def :refer [defstyles]]))

(def scroll-bar-x [:.scroll-bar-x
                   {:width "100%"
                    :overflow-x "auto"
                    :overflow-y "hidden"}])

(def scroll-bar-y [:.scroll-bar-y
                   {:height "100%"
                    :overflow-x "hidden"
                    :overflow-y "auto"}])


(defstyles style
  [:#app {:top "0"
          :left "0"
          :width "100%"
          :height "100%"}

   [:.grid-wrapper {:width "100%"
                    :height "100%"
                    :overflow "hidden"}

    [scroll-bar-y]
    [scroll-bar-x]

    [:.scrollbar-measure {:width "100px"
                          :height "100px"
                          :overflow "scroll"
                          :position "absolute"
                          :top "-9999px"}]]])
