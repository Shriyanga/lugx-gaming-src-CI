#!/bin/bash

VERSION=""

# Get CLI argument
while getopts v: flag
do
  case "${flag}" in
    v) VERSION=${OPTARG};;
  esac
done

# Fetch latest tags
git fetch --tags --force
CURRENT_VERSION=$(git describe --abbrev=0 --tags 2>/dev/null)

# Default to v0.1.0 if no tags
if [[ "$CURRENT_VERSION" == "" ]]; then
  CURRENT_VERSION="v0.1.1"
fi
echo "Current Version: $CURRENT_VERSION"

# Split version
CURRENT_VERSION_PARTS=(${CURRENT_VERSION//./ })
VNUM1=${CURRENT_VERSION_PARTS[0]//v/}
VNUM2=${CURRENT_VERSION_PARTS[1]}
VNUM3=${CURRENT_VERSION_PARTS[2]}

# Bump version
if [[ "$VERSION" == "major" ]]; then
  VNUM1=$((VNUM1 + 1))
  VNUM2=0
  VNUM3=0
elif [[ "$VERSION" == "minor" ]]; then
  VNUM2=$((VNUM2 + 1))
  VNUM3=0
elif [[ "$VERSION" == "patch" ]]; then
  VNUM3=$((VNUM3 + 1))
else
  echo "Invalid version type. Use: -v [major|minor|patch]"
  exit 1
fi

NEW_TAG="v$VNUM1.$VNUM2.$VNUM3"
echo "New tag: $NEW_TAG"

# Check if this commit already has a tag
GIT_COMMIT=$(git rev-parse HEAD)
EXISTING_TAG=$(git tag --points-at $GIT_COMMIT)

if [[ "$EXISTING_TAG" != *"$NEW_TAG"* ]]; then
  git config user.name "github-actions"
  git config user.email "github-actions@github.com"
  git tag "$NEW_TAG"
  git push origin "$NEW_TAG"
  echo "Tagged $GIT_COMMIT with $NEW_TAG"
else
  echo "This commit already has a tag: $EXISTING_TAG"
fi

# Output for GitHub Actions
echo "git-tag=$NEW_TAG" >> "$GITHUB_OUTPUT"
