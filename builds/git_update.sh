#!/bin/bash

# Default version increment type
VERSION=""

# Get version bump type from flags
while getopts v: flag
do
  case "${flag}" in
    v) VERSION=${OPTARG};;
  esac
done

# Fetch all tags to ensure we get latest
git fetch --tags --force

# Get current latest tag or fallback to v0.1.0
CURRENT_VERSION=$(git describe --abbrev=0 --tags 2>/dev/null)

if [[ -z "$CURRENT_VERSION" ]]; then
  CURRENT_VERSION="v0.1.0"
fi

echo " Current Version: $CURRENT_VERSION"

# Parse version numbers (remove 'v' and split)
CURRENT_VERSION_PARTS=(${CURRENT_VERSION//./ })
VNUM1=${CURRENT_VERSION_PARTS[0]//v/}
VNUM2=${CURRENT_VERSION_PARTS[1]}
VNUM3=${CURRENT_VERSION_PARTS[2]}

# Perform version bump
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
  echo "❌ Invalid version type. Use: -v [major|minor|patch]"
  exit 1
fi

# Create new tag
NEW_TAG="v$VNUM1.$VNUM2.$VNUM3"
echo " ($VERSION) Bumping version: $CURRENT_VERSION → $NEW_TAG"

# Check if current commit is already tagged
GIT_COMMIT=$(git rev-parse HEAD)
NEEDS_TAG=$(git describe --contains "$GIT_COMMIT" 2>/dev/null)

if [[ -z "$NEEDS_TAG" ]]; then
  git config user.email "ci-bot@example.com"
  git config user.name "CI Bot"
  git tag "$NEW_TAG"
  git push origin "$NEW_TAG"
  echo "  Tagged current commit with: $NEW_TAG"
else
  echo "ℹ  Current commit already has tag: $NEEDS_TAG"
fi

# Output for GitHub Actions
echo "git-tag=$NEW_TAG" >> "$GITHUB_OUTPUT"

exit 0
