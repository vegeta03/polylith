(ns polylith.clj.core.shell.candidate.selector.ws-tag-patterns
  (:require [polylith.clj.core.autocomplete.interface :as a]))

(defn tag-keys [tag-pattern-key]
  (let [tag-name (name tag-pattern-key)]
    [tag-name (str "previous-" tag-name)]))

(defn select [candidate _ {:keys [settings]}]
  (let [group-id (-> candidate :group :id)]
    (map #(a/single-txt % (a/group group-id))
         (mapcat tag-keys
                 (-> settings :tag-patterns keys)))))
