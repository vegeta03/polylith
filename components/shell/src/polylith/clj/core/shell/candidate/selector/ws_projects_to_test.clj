(ns polylith.clj.core.shell.candidate.selector.ws-projects-to-test
  (:require [clojure.set :as set]
            [polylith.clj.core.autocomplete.interface :as a]
            [polylith.clj.core.shell.candidate.shared :as shared]
            [polylith.clj.core.util.interface.color :as color]))

(defn fn-explorer-child [value entity color-mode group select-fn]
  (a/candidate (str value ":")
               (color/entity entity value color-mode)
               value :fn [true
                          {:type :fn
                           :stay? true
                           :group group
                           :function select-fn}]))

(defn select
  "The idea with the project:P1:P2 parameter is to select which projects
   to test, and that's why we only return projects that are marked for
   testing (we don't need to filter out already filtered out projects)."
  [{:keys [group]} groups {:keys [settings changes]}]
  (let [color-mode (:color-mode settings)
        {:keys [project-to-bricks-to-test
                project-to-projects-to-test]} changes]
    (mapv #(fn-explorer-child % :project color-mode group #'select)
          (sort (set/difference
                  (set (concat ["development"]
                               (map first (filter #(-> % second seq) project-to-bricks-to-test))
                               (map first (filter #(-> % second seq) project-to-projects-to-test))))
                  (set (shared/args groups group)))))))
