function helpMsg {
	echo ""
	echo "Command syntax is :"
	echo "copy [target directory name]"
	echo ""
}
if [[ "$1" == "-h" ]]; then
	helpMsg;
	exit 0;
fi

if [[ "$1" == "" ]]; then
	helpMsg;
	exit 0;
fi
destPath=$1

destPath=$destPath$(pubDate +"%m-%d-%y")
echo "Cp to:" $destPath
rsync -av --progress . $destPath --exclude build --exclude .git --exclude .idea
