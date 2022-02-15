(ns polylith.clj.core.workspace.settings
  (:require [polylith.clj.core.workspace.settings.projects :as projects]
            [polylith.clj.core.workspace.settings.commands :as commands]))

(defn enrich-settings [settings projects]
  (assoc settings :commands (commands/commands-spec settings)
                  :projects (projects/missing-aliases settings projects)))
