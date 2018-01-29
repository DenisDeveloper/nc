(ns netcost.filter-block
  (:require [reagent.core :as reagent :refer [atom]]))

(defn- search-bar []
  [:div.search-bar
   [:i {:class "fa fa-search" :aria-hidden "true"}]
   [:div.input "Маршрут, накладная, машина"]
   [:div.date "ЯНВАРЬ 2017"]
   [:div.toolbox
    [:i {:class "fa fa-cog" :aria-hidden "true"}]
    [:i {:class "fa fa-download" :aria-hidden "true"}]
    [:i {:class "fa fa-filter" :aria-hidden "true"}]]])

(defn- tag-bar []
  [:div.tag-bar
   [:div.tags
    [:div.tag "ДЛ"]
    [:div.tag "ДЛТ"]]
   [:div.currency
    [:div {:class "currency-item left-item selected"} "Копейки"]
    [:div {:class "currency-item currency"} "Рубли"]
    [:div {:class "currency-item currency"} "Тысячи"]
    [:div {:class "currency-item right-item currency"} "Миллионы"]]])
    

    

(defn- -filter-block []
  [:div.filter-block
   [search-bar]
   [tag-bar]])

(defn filter-block []
  (reagent/create-class {:component-did-mount #(prn "filter-block did mount")
                         :reagent-render (fn [] [-filter-block])}))


  ;; <div class="filter-box">
  ;;   <div class="search-bar">
  ;;     <i class="fa fa-search" aria-hidden="true"></i>
  ;;     <div class="input">Маршрут, накладная, машина</div>
  ;;     <div class="date">{{"апрель 2017".toUpperCase()}}</div>
  ;;     <div class="toolbox">
  ;;       <i class="fa fa-cog" @click="showSettings" aria-hidden="true"></i>
  ;;       <i class="fa fa-download" aria-hidden="true"></i>
  ;;       <i class="fa fa-filter" aria-hidden="true"></i>
  ;;     </div>
  ;;   </div>
  ;;   <div class="tag-bar">
  ;;     <div class="tags">
  ;;       <div class="tag">ДЛ</div>
  ;;       <div class="tag">ДЛТ</div>
  ;;     </div>
  ;;     <div class="currency">
  ;;       <div :class="currency === 0 && 'selected'" v-on:click="onCurrency(0)" class="currency-item left-item">Копейки</div>
  ;;       <div :class="currency === 1 && 'selected'" v-on:click="onCurrency(1)" class="currency-item">Рубли</div>
  ;;       <div :class="currency === 2 && 'selected'" v-on:click="onCurrency(2)" class="currency-item">Тысячи</div>
  ;;       <div :class="currency === 3 && 'selected'" v-on:click="onCurrency(3)" class="currency-item right-item">Миллионы</div>
  ;;     </div>
  ;;   </div>
  ;; </div>
