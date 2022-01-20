(ns polylith.clj.core.shell.candidate.selector.ws-bricks
  (:require [clojure.set :as set]
            [polylith.clj.core.autocomplete.interface.v1 :as a]
            [polylith.clj.core.shell.candidate.shared :as shared]))

(defn candidate [brick-name group select-fn base-names color-mode]
  (let [colored-brick (shared/colored-brick brick-name base-names color-mode)]
    (a/candidate (str brick-name ":")
                 colored-brick
                 brick-name
                 :fn [true
                      {:function select-fn
                       :group group}])))

(defn select [{:keys [group]} groups {:keys [settings components bases]}]
  (let [color-mode (:color-mode settings)
        base-names (set (map :name bases))]
    (mapv #(candidate % group #'select base-names color-mode)
          (set/difference (set (conj (map :name (concat components bases))
                                     "-"))
                          (set (shared/args groups group))))))
