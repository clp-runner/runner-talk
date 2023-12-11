# pre-commit checks

.PHONY: githooks
githooks: .git/hooks/commit-msg

.git/hooks/%: hack/git/hooks/%
	@mkdir -p .git/hooks
	cp hack/git/hooks/$* .git/hooks/$*
