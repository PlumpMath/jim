(ns jim.cards.cards
  (:require [jim.ui :as ui]
            [cljs.test :as test])
  (:require-macros [sablono.core :refer [html]]
                   [devcards.core :as dc :refer [defcard deftest]]))

(defcard first-card
  (html [:div {:on-click #(js/alert "JIM")}
              [:h1 "This is NOT your first devcard!"]]))

(defcard new-card
  "### New Card"
  (fn [state]
    (ui/my-new-card state))
  {:text "Text"}
  {:inspect-data true
   :history true})

; (defn toggle-todo [state row]
;   (swap! state assoc-in [:data (.indexOf (get @state :data) row) :done] (not (get row :done))))
;
; (defn draw-list [state]
;   (html
;     [:table
;      [:tbody
;       (for [row (get @state :data)]
;         [:tr {:key (get row :id)}
;          [:td (get row :todo)]
;          [:td [:input {:type "checkbox"
;                        :checked (get row :done)
;                        :name (str "checkbox-" (get row :id))
;                        :on-change #(toggle-todo state row)}]]])]])
;
;   (defcard to-do-list
;            "### TODO List"
;            (fn [state]
;              (draw-list state))
;
;            {
;             :headers ["todo" "done"]
;             :data    [
;                       {:id 1 :todo "NRIC Card" :done false}
;                       {:id 2 :todo "EP" :done false}
;                       {:id 3 :todo "Shopping" :done true}]}
;
;            {
;             :inspect-data true
;             :history      true}))
;
;
; (deftest my-test
;  (test/testing "equality" (test/is (= 1 1))))
