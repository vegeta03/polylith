(ns polylith.clj.core.workspace.settings.projects
  (:require [clojure.set :as set]))

(defn undefined-project [index project-name]
  [project-name {:alias (str "?" (inc index))}])

(defn missing-aliases [settings projects]
  "Enrich project aliases with missing aliases, e.g.: '?1', '?2'"
  (let [conf-projects (merge {"development" {:alias "dev"}} (:projects settings))
        undefined-projects (set/difference (set (map :name projects))
                                           (set (map first (filter #(-> % second :alias)
                                                                   conf-projects))))]
    (merge (into {} (map-indexed undefined-project undefined-projects))
           conf-projects)))
