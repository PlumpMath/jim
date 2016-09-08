(ns jim.ui
  (:require-macros [sablono.core :refer [html]])
  (:require [jim.state :as state]
            [cljsjs.react]
            [cljsjs.react.dom]))

(defn my-new-card [state]
  (html [:h1 {:on-click #(state/text! state "New value")}
          (state/text state)]))

(defn button
  [text action]
  (html [:input {:type "button"
                 :value text
                 :on-click action}]))



(defmulti page
  (fn [state] (get-in @state [:ui :page-shown] :page-1)))


(defmethod page :page-1
  [state]
  (html [:div {:key "page-1"
               :class "page"
               :style {:background-color "blue"}}
          "PAGE 1"
          (button "page 2" #(swap! state assoc-in [:ui :page-shown] :page-2))]))

(defmethod page :page-2
  [state]
  (html [:div {:key "page-2"
               :class "page"
               :style {:background-color "red"}}
          "PAGE 2"
          (button "page 1" #(swap! state assoc-in [:ui :page-shown] :page-1))]))


(defn app
  "Render application"
  [state]
  (.createElement
    js/React js/React.addons.CSSTransitionGroup
    #js {:transitionName "apptransition"
         :transistionTimeout 1000
         :transitionEnterTimeout 1000
         :transitionLeaveTimeout 1000
         :transitionAppear true
         :transitionAppearTimeout 1000}
    (page state))
  #_(html [:div {} "APP"]
          (my-new-card state)
          [:input {:type "button"
                   :on-click #(state/emit state/pipe [:click state])
                   :value "Press me"}]
          [:input {:type "button"
                   :on-click #(state/emit state/pipe [:click-too state])
                   :value "Press me too"}]))
