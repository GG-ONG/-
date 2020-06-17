> MarkDown 활용법에 대해 간단히 검색해보세요.

<<<<<<< HEAD

# 기말텀프로젝트 간단 기획

 1. 게임 개요

        비행기를 사용자의 터치를 이용하여 조작하는 횡스크롤게임

 2. 게임 목표

        적을 터치를 이용해 피해가며 특정 물체와 부딪치면 hp가 깎이고 특정 물체를 먹으면 점수가 올라간다.

 3. 게임 진행

        스타트 버튼을 누르면 비행기가 스폰되고 hp를 깎는 물체와 점수를 얻을 수 있는 물체가 랜덤으로 다가온다.

        hp는 최대 5이며 다섯 번 hp를 깎는 물체에 맞으면 게임 종료 최대 스코어를 표시한다

        점수를 얻는 물체를 먹으면 스코어가 올라간다.

        제한시간은 없다.

 4. 게임 옵션

        hp 숫자를 줄이거나 다가오는 물체의 스피드를 조정할 수 있다.

# 구현할것

 - 게임뷰를 설정하고 게임월드를 만든다
 - 터치를 입력 받을 컨트롤러를 구현한다.
 - 컨트롤러와 연동된 비행기를 구현한다.
 - 물체 이미지와 비행기 이미지를 구한다.
 - 날라오는 물체의 배치를 어떤 스피드로 어느 위치에서 어떤 순서로 할지 생각해본다(랜덤함수를 생각해본다)
 - 피격판정을 구현한다.
 - hp를 깎는 물체와 점수를 올려주는 물체를 구분한다. 그렇지 않으면 hp를 깎는 물체를 맞아도 점수가 오를 수 있다.
 - 각각 점수를 얻었을때와 hp가 깎였을때 그것을 반영할 수 있는 인터페이스가 필요하다.
 - hp가 0이 되면 게임 종료 화면이 떠야한다.
 - 다시하기나 게임종료 버튼이 있다.
 - 다시하기를 누르면 게임이 다시 시작된다.
 - 게임 종료를 누르면 정말 종료하시겠습니까? 라는 물음이 나오며 다시 확인한다. 네 -> 종료, 아니오 -> 마지막 화면을 기억했다가 그 화면을 다시 띄운다.

# 수행 내역 

4.28 : 최초 기획서를 업로드 했습니다.

5.1 : 피드백을 받고 마크다운 스타일로 수정했습니다.

5.17 : 본격적으로 게임을 만들기 시작했습니다. KPU로고를 이용하여 마우스 클릭을 받으면 날 수 있고 클릭을 멈추면 떨어지는 날개를 구현했습니다.

5.18 : 게임 배경을 KPU 백그라운드로 구현했습니다. 점수 표시와 HP를 나타내는 UI를 구현했습니다.

5.19 : 날개 애니메이션을 추가했습니다.

5.20 : "랜덤"으로 날라오는 파랑볼을 구현, 파랑볼을 먹으면 점수를 얻을 수 있게 구현했습니다. 파랑볼을 먹으면 점수를 얻어야 하니 당연히 피격판정도 구현을 하였습니다.

5.26 : "랜덤"으로 날라오는 검은볼 추가, 이걸 먹으면 HP가 깎이게 만들 예정입니다. 

5.31 : HP 깎임 구현 

6.3 : 게임 시작 화면 구현

6.4 : 게임 시작 버튼 구현, 게임 오버 화면 구현

6.6 : 하이스코어 기능 추가

6.7 : 점수, 피격 사운드 추가

6.9 : BGM 신나는 노래로 추가, KPU교가를 찾을 수가 없음

6.11 : 파랑볼 스피드 조정, 50점 얻으면 레벨증가 구현, 블랙볼을 다른 이미지로 변경

6.14 : 테스트 동영상 구글 광고 추가

6.17 : 화면 호환 시도, 잘안됨, 매직넘버 최대한 없애기

# 추가할것
- 다시하기, 게임종료 UI 추가(완료)
- 파랑볼이나 검은볼대신 참신한 이미지가 있는지 찾아보기(완료)
- 날라오는 물체 속도를 밸런스있게 조정해보기 (완료)
- 하이스코어 저장하기(완료)
- 점수가 올라갈수록 레벨 올라가기(완료)
- 사운드 추가(완료)
- BGM재생,KPU 교가를 추가(완료)
- 광고 추가 해보기(완료)
- 알수없는 앱 중지 팝업 뜨는거 고쳐보기(완료)
- 다른 기기도 호환 가능하게 하기, 테스트 결과 호환성이 좋지 않음(완료)

