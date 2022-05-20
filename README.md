# VALUE WAVE

해당 프로젝트의 레포지토리는 다음과 같습니다:
* [value-wave-server](https://github.com/pomeloEater/value-wave-server)
* [value-wave-client](https://github.com/pomeloEater/value-wave-client)

## stack
```
- frontend : React, styled-component, react-redux, redux-toolkit, yarn
- backend : Java 11, Spring Boot, Gradle
- api : Kakao (Local/Map) API
```

## 환경설정

### install
* [Visual Studio Code](https://code.visualstudio.com/download)
* [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/#section=windows)
* [Node.js](https://nodejs.org/dist/v16.15.0/) 16.15.0
* [yarn](https://classic.yarnpkg.com/lang/en/docs/install/#windows-stable)

### Clone Repositories
```shell
git clone https://github.com/pomeloEater/value-wave-server.git value-wave
cd value-wave
git clone https://github.com/pomeloEater/value-wave-client.git frontend
```

### ignored API key files
* src/main/resources/application-api.yml
* frontend/.env

### frontend 의존성 설치 시 node_modules 폴더가 생성되는 경우
```shell
# frontend 폴더에서 진행해야 합니다
yarn set version berry
rm -rf node_modules
yarn install
# 다시 node_modules가 설치되는 경우 .yarnrc.yml 파일 확인 필요
# yarn add #dependency_name
```
