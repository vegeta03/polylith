(ns polylith.clj.core.workspace.settings.commands
  (:require [polylith.clj.core.util.interface :as util]))

(defn command [[command command-ns]]
  (require command-ns)
  (let [spec (var-get (util/variable command-ns "specification"))
        execute-fn (util/variable command-ns "execute")]
    [command (assoc spec :execute-fn execute-fn)]))

(defn commands-spec [{:keys [commands]}]
  (into {} (map command commands)))
