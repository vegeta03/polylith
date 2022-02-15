(ns polylith.clj.core.help.text-table.main-commands
  (:require [polylith.clj.core.help.shared :as s]
            [polylith.clj.core.help.text-table.table :as table]))

(defn commands [cm]
  [{:command "check"
    :description "Checks if the workspace is valid."}
   {:command (str "create " (s/key "E" cm) " name:" (s/key "N" cm) " [" (s/key "ARG" cm) "]")
    :description "Creates a component, base, project or workspace."}
   {:command (str "deps [project:" (s/key "P" cm) "] [brick:" (s/key "B" cm) "]")
    :description "Shows dependencies."}
   {:command "diff"
    :description "Shows changed files since last stable point in time."}
   {:command (str "help [" (s/key "C" cm) "] [" (s/key "ARG" cm) "]")
    :description "Shows this help or help for specified command."}
   {:command (str "info [" (s/key "ARGS" cm) "]")
    :description "Shows a workspace overview and checks if it's valid."}
   {:command "libs"
    :description "Shows all libraries in the workspace."}
   {:command "shell"
    :description "Starts an interactive shell."}
   {:command (str "test [" (s/key "ARGS" cm) "]")
    :description "Runs tests."}
   {:command "version"
    :description "Shows current version of the tool."}
   {:command (str "ws [get:" (s/key "X" cm) "]")
    :description "Shows the workspace as data."}])

(defn print-table [color-mode]
  (table/print-table color-mode (commands color-mode)))
