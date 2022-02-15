(ns polylith.clj.core.command.custom.hello
  (:require [polylith.clj.core.autocomplete.interface.v1 :as a]
            [clojure.string :as str]))

(defn execute [args _]
  (println (str "Hello " (str/join " " args) "!")))

(def specification
  {:auto-complete (a/single-txt "hello")
   :help {:overview {:command "hello"
                     :description "Prints out hello."}
          :description ["Prints out the passed in arguments followed by a !."
                        ""
                        "This command is an example of a custom command."]
          :examples ["hello"
                     "hello world"]}})
