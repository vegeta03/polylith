(ns polylith.clj.core.shell.candidate.specification
  [:require [polylith.clj.core.common.interface :as common]
            [polylith.clj.core.autocomplete.interface :as a]
            [polylith.clj.core.shell.candidate.custom-cmd :as custom-cmd]
            [polylith.clj.core.shell.candidate.selector.remote-branches :as remote-branches]
            [polylith.clj.core.shell.candidate.selector.ws-bricks :as ws-bricks]
            [polylith.clj.core.shell.candidate.selector.ws-explore :as ws-explore]
            [polylith.clj.core.shell.candidate.selector.file-explorer :as file-explorer]
            [polylith.clj.core.shell.candidate.selector.ws-tag-patterns :as ws-tag-patterns]
            [polylith.clj.core.shell.candidate.selector.ws-deps-entities :as ws-deps-entities]
            [polylith.clj.core.shell.candidate.selector.ws-projects-to-test :as ws-projects-to-test]]
  (:refer-clojure :exclude [load test]))

;; check
(def check (a/single-txt "check"))

;; create
(def create-base-name (a/multi-param "name"))
(def create-base (a/single-txt "base" [create-base-name]))
(def interface-value (a/multi-arg :create-component "interface"))
(def interface (a/multi-param "interface" 2 (a/group :create-component) (a/optional) [interface-value]))
(def create-component-name-value (a/multi-arg :create-component "name"))
(def create-component-name (a/multi-param "name" 1 (a/group :create-component) [create-component-name-value]))
(def create-component (a/single-txt "component" :create-component [create-component-name interface]))
(def create-project-name (a/multi-param "name"))
(def create-project (a/single-txt "project" [create-project-name]))
(def create (a/single-txt "create" :create [create-base create-component create-project]))

;; deps
(def deps-brick (a/fn-explorer "brick" :deps #'ws-deps-entities/select-bricks))
(def deps-project (a/fn-explorer "project" :deps #'ws-deps-entities/select-projects))
(def deps (a/single-txt "deps" :deps [deps-brick deps-project]))

;; diff
(def diff-since (a/fn-explorer "since" :diff #'ws-tag-patterns/select))
(def diff (a/single-txt "diff" :diff [diff-since]))

;; help
(def help-create-base (a/single-txt "base"))
(def help-create-component (a/single-txt "component"))
(def help-create-project (a/single-txt "project"))
(def help-create-workspace (a/single-txt "workspace"))
(def help-create (a/single-txt "create" [help-create-base help-create-component help-create-project help-create-workspace]))
(def help-deps-project (a/flag "project" :help-deps))
(def help-deps-brick (a/flag "brick" :help-deps))
(def help-deps (a/single-txt "deps" :help-deps [help-deps-brick help-deps-project]))
(def help (a/single-txt "help" (vec (concat [help-create help-deps]
                                            (mapv #(a/single-txt %)
                                                  ["check" "diff" "info" "libs" "switch-ws" "shell" "tap" "test" "version" "ws"])))))

;; info
(def info-since (a/fn-explorer "since" :info #'ws-tag-patterns/select))
(def info-project (a/fn-explorer "project" :info #'ws-projects-to-test/select))
(def info-brick (a/fn-explorer "brick" :info #'ws-bricks/select))
(def info-resources (a/flag "resources" :info))
(def info-project-flag (a/flag-explicit "project" :info))
(def info-dev (a/flag "dev" :info))
(def info-loc (a/flag "loc" :info))
(def info-all-bricks (a/flag "all-bricks" :info))
(def info-all (a/flag "all" :info))

(defn info [profiles]
  (a/single-txt "info" :info
                (concat profiles
                        [info-all info-all-bricks info-brick info-loc info-dev
                         info-resources info-project info-project-flag info-since])))

;; libs
(def libs (a/single-txt "libs" :libs))

;; test
(def test-since (a/fn-explorer "since" :test #'ws-tag-patterns/select))
(def test-project (a/fn-explorer "project" :test #'ws-projects-to-test/select))
(def test-brick (a/fn-explorer "brick" :test #'ws-bricks/select))
(def test-project-flag (a/flag-explicit "project" :test))
(def test-dev (a/flag "dev" :test))
(def test-loc (a/flag "loc" :test))
(def test-verbose (a/flag "verbose" :test))
(def test-all-bricks (a/flag "all-bricks" :test))
(def test-all (a/flag "all" :test))

(defn test [profiles]
  (a/single-txt "test" :test
                (vec (concat [test-all test-all-bricks test-brick test-loc test-verbose
                              test-dev test-project test-project-flag test-since]
                             profiles))))

;; version
(def version (a/single-txt "version"))

;; migrate
(defn migrate [show-migrate?]
  (when show-migrate?
    [(a/single-txt "migrate")]))

(def ws-branch (a/fn-explorer "branch" :ws #'remote-branches/select))
(def ws-project (a/multi-fn "project" (a/group :ws) (a/function #'ws-projects-to-test/select)))
(def ws-brick (a/multi-fn "brick" (a/group :ws) (a/function #'ws-bricks/select)))
(def ws-project-flag (a/flag-explicit "project" :ws))
(def ws-dev (a/flag "dev" :ws))
(def ws-latest-sha (a/flag "latest-sha" :ws))
(def ws-loc (a/flag "loc" :ws))
(def ws-all-bricks (a/flag "all-bricks" :ws))
(def ws-all (a/flag "all" :ws))
(def ws-since (a/fn-explorer "since" :ws #'ws-tag-patterns/select))
(def ws-out (a/fn-explorer "out" :ws #'file-explorer/select))
(def ws-get (a/multi-fn "get" (a/group :ws) (a/function #'ws-explore/select)))

;; ws
(defn ws [profiles]
  (a/single-txt "ws" :ws
                (vec (concat [ws-project ws-brick ws-project-flag ws-dev ws-latest-sha
                              ws-loc ws-all-bricks ws-all ws-get ws-out ws-since ws-branch]
                             profiles))))

(def switch-ws-dir (a/fn-explorer "dir" :switch-ws #'file-explorer/select))
(def switch-ws-file (a/fn-explorer "file" :switch-ws #'file-explorer/select))
(def switch-ws (a/single-txt "switch-ws" :switch-ws [switch-ws-file switch-ws-dir]))

(defn profiles [group-id settings]
  (map #(a/group-arg (str "+" %) group-id (str "+" %))
       (-> settings :profile-to-settings keys)))

;----------------------


(defn candidates [{:keys [settings user-input] :as workspace}]
  (let [{:keys [ws-dir ws-file]} user-input
        custom-commands (a/single-txt "x" (custom-cmd/specs workspace))
        show-migrate? (common/toolsdeps1? workspace)
        info-profiles (profiles :info settings)
        test-profiles (profiles :test settings)
        ws-profiles (profiles :ws settings)
        current-ws? (or (nil? ws-file)
                        (or (nil? ws-dir)
                            (= "." ws-dir)))]
    (vec (concat [check
                  deps
                  diff
                  help
                  libs
                  version
                  switch-ws
                  custom-commands
                  (info info-profiles)
                  (ws ws-profiles)]
                 (migrate show-migrate?)
                 (if current-ws?
                   [create
                    (test test-profiles)]
                   [])))))

;; create workspace
(def create-workspace-branch (a/multi-param "branch"))
(def create-workspace-top-ns-value (a/group-arg "" :create-workspace "top-ns" false))
(def create-workspace-top-ns (a/multi-param "top-ns" (a/group :create-workspace) [create-workspace-top-ns-value]))
(def create-workspace-name-value (a/group-arg "" :create-workspace "name" false))
(def create-workspace-name (a/multi-param "name" 1 (a/group :create-workspace) [create-workspace-name-value]))
(def create-workspace (a/single-txt "workspace" :create-workspace [create-workspace-name create-workspace-top-ns create-workspace-branch]))
(def create-outside-ws-root (a/single-txt "create" [create-workspace]))

(def candidates-outside-ws-root [help version create-outside-ws-root])
