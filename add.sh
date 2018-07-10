git config --global user.email "shaomiaojava@gmail.com"
git config --global user.name "tea9"

git status

echo "更新仓库 ...(放心，我们不会删除之前的更改。)"

git pull origin master

clear

git status
echo "---------------------------"
echo "核实信息。"

echo "你的更改已成功。我们将会上传到仓库。"
echo ""
echo "输入更改留言在下方。"
echo ""
read -p "更改的留言是:"

rm *~
rm **/*~
rm **/**/*~

git add .
git stage .
git commit -a -m  "${REPLY}"

echo "更改留言设置成功!"
echo ""
echo "查看核实信息!"

clear

git status

git gc

clear

git push

clear

echo "上传到仓库成功!"

clear 

echo ""
git gc
echo "---------------------------"
echo "关机~"