# AddressJson

## Git Repo Clone https:

git clone https://github.com/dominikajoubert/addressjson.git

## Git Repo Clone ssh:
ssh clone (requires ssh key setup on user profile in github)
git clone git@github.com:dominikajoubert/addressjson.git


## Adding Lombok to project in intelliJ if not already available.

https://projectlombok.org/setup/intellij
https://stackoverflow.com/questions/41161076/adding-lombok-plugin-to-intellij-project

## To build project jar, run below

gradle clean build

## Jar built in below folder

\addressjson\build\libs

## Run jar

java -jar  addressjson.jar

Data to be entered by user:
1) You will be prompted to enter the path and file name of the file you wish to use.
2) You will be prompted to select which address type you wish to view (POSTAL,BUSINESS,PHYSICAL). If none is entered then all address type details will be shown.

Data displayed on screen:
1) All address in the file will be pretty printed.
2) Either all address types pretty printed or only the one entered above.
3) All address validated, and addresses displayed with the reason why they are invalid/valid.
