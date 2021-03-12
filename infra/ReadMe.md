- ec2立てる
- ポリシーアタッチする
  - AmazonEC2RoleforSSM
  - AmazonEC2ContainerRegistryReadOnly
  - AmazonS3ReadOnlyAccess
- SSMでアクセスする
```
sudo yum install -y docker
sudo systemctl start docker
sudo usermod -g docker ec2-user
sudo systemctl enable docker
sudo curl -L https://github.com/docker/compose/releases/download/1.28.5/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```
- git のインストール
```
sudo yum install git
```
- awsの設定
```
aws configure
# region -> ap-northeast-1
```
- docker imageをpull
```
$(aws ecr get-login --no-include-email --region ap-northeast-1)
docker pull 072514135421.dkr.ecr.ap-northeast-1.amazonaws.com/tsk2web
```
- .bash_profileに追加
```
AWS_ACOUNT_ID=${AWS_ACOUNT_ID}
```