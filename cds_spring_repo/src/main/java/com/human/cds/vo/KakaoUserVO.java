package com.human.cds.vo;

import java.util.Date;
import java.util.HashMap;

public class KakaoUserVO {
	
	
    private Long id; //회원 번호
    private Boolean has_signed_up; //true : 연결 상태, false : 연결 대기 상태
    private Date connected_at; //서비스에 연결 완료된 시각. UTC
    private Date synched_at; //카카오싱크 간편가입을 통해 로그인한 시각. UTC
    private HashMap<String, String> properties; //사용자 프로퍼티
    private KakaoAccount kakao_account; //카카오 계정 정보
    private Partner for_partner; //uuid 등 추가 정보

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getHas_signed_up() {
		return has_signed_up;
	}
	public void setHas_signed_up(Boolean has_signed_up) {
		this.has_signed_up = has_signed_up;
	}
	public Date getConnected_at() {
		return connected_at;
	}
	public void setConnected_at(Date connected_at) {
		this.connected_at = connected_at;
	}
	public Date getSynched_at() {
		return synched_at;
	}
	public void setSynched_at(Date synched_at) {
		this.synched_at = synched_at;
	}
	public HashMap<String, String> getProperties() {
		return properties;
	}
	public void setProperties(HashMap<String, String> properties) {
		this.properties = properties;
	}
	public KakaoAccount getKakao_account() {
		return kakao_account;
	}
	public void setKakao_account(KakaoAccount kakao_account) {
		this.kakao_account = kakao_account;
	}
	public Partner getFor_partner() {
		return for_partner;
	}
	public void setFor_partner(Partner for_partner) {
		this.for_partner = for_partner;
	}

    public class KakaoAccount {
        
        private Boolean profile_needs_agreement; //프로필 정보 제공 동의 여부
        private Boolean profile_nickname_needs_agreement; //닉네임 제공 동의 여부
        private Boolean profile_image_needs_agreement; //프로필 사진 제공 동의 여부
        private Profile profile; //사용자 프로필 정보
        private Boolean name_needs_agreement; //이름 제공 동의 여부
        private String name; //카카오계정 이름
        private Boolean email_needs_agreement; //이메일 제공 동의 여부
        private Boolean is_email_valid; // true : 유효한 이메일, false : 이메일이 다른 카카오 계정에 사용돼 만료
        private Boolean is_email_verified; //true : 인증된 이메일, false : 인증되지 않은 이메일
        private String email; //이메일
        private Boolean age_range_needs_agreement; //연령대 제공 동의 여부
        private String age_range; //연령대
        private Boolean birthyear_needs_agreement; //출생 연도 제공 동의 여부
        private String birthyear; //출생 연도 (YYYY 형식)
        private Boolean birthday_needs_agreement; //생일 제공 동의 여부
        private String birthday; //생일 (MMDD 형식)
        private String birthday_type; // SOLAR(양력) 혹은 LUNAR(음력)
        private Boolean gender_needs_agreement; //성별 제공 동의 여부
        private String gender; //성별
        private Boolean phone_number_needs_agreement; //전화번호 제공 동의 여부
        private String phone_number; //국내 번호인 경우 +82 00-0000-0000 형식
        private Boolean ci_needs_agreement; //CI 동의 여부
        private String ci; //CI, 연계 정보
        private Date ci_authenticated_at; //CI 발급 시각, UTC

        public Boolean getProfile_needs_agreement() {
			return profile_needs_agreement;
		}
		public void setProfile_needs_agreement(Boolean profile_needs_agreement) {
			this.profile_needs_agreement = profile_needs_agreement;
		}
		public Boolean getProfile_nickname_needs_agreement() {
			return profile_nickname_needs_agreement;
		}
		public void setProfile_nickname_needs_agreement(Boolean profile_nickname_needs_agreement) {
			this.profile_nickname_needs_agreement = profile_nickname_needs_agreement;
		}
		public Boolean getProfile_image_needs_agreement() {
			return profile_image_needs_agreement;
		}
		public void setProfile_image_needs_agreement(Boolean profile_image_needs_agreement) {
			this.profile_image_needs_agreement = profile_image_needs_agreement;
		}
		public Profile getProfile() {
			return profile;
		}
		public void setProfile(Profile profile) {
			this.profile = profile;
		}
		public Boolean getName_needs_agreement() {
			return name_needs_agreement;
		}
		public void setName_needs_agreement(Boolean name_needs_agreement) {
			this.name_needs_agreement = name_needs_agreement;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Boolean getEmail_needs_agreement() {
			return email_needs_agreement;
		}
		public void setEmail_needs_agreement(Boolean email_needs_agreement) {
			this.email_needs_agreement = email_needs_agreement;
		}
		public Boolean getIs_email_valid() {
			return is_email_valid;
		}
		public void setIs_email_valid(Boolean is_email_valid) {
			this.is_email_valid = is_email_valid;
		}
		public Boolean getIs_email_verified() {
			return is_email_verified;
		}
		public void setIs_email_verified(Boolean is_email_verified) {
			this.is_email_verified = is_email_verified;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Boolean getAge_range_needs_agreement() {
			return age_range_needs_agreement;
		}
		public void setAge_range_needs_agreement(Boolean age_range_needs_agreement) {
			this.age_range_needs_agreement = age_range_needs_agreement;
		}
		public String getAge_range() {
			return age_range;
		}
		public void setAge_range(String age_range) {
			this.age_range = age_range;
		}
		public Boolean getBirthyear_needs_agreement() {
			return birthyear_needs_agreement;
		}
		public void setBirthyear_needs_agreement(Boolean birthyear_needs_agreement) {
			this.birthyear_needs_agreement = birthyear_needs_agreement;
		}
		public String getBirthyear() {
			return birthyear;
		}
		public void setBirthyear(String birthyear) {
			this.birthyear = birthyear;
		}
		public Boolean getBirthday_needs_agreement() {
			return birthday_needs_agreement;
		}
		public void setBirthday_needs_agreement(Boolean birthday_needs_agreement) {
			this.birthday_needs_agreement = birthday_needs_agreement;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getBirthday_type() {
			return birthday_type;
		}
		public void setBirthday_type(String birthday_type) {
			this.birthday_type = birthday_type;
		}
		public Boolean getGender_needs_agreement() {
			return gender_needs_agreement;
		}
		public void setGender_needs_agreement(Boolean gender_needs_agreement) {
			this.gender_needs_agreement = gender_needs_agreement;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public Boolean getPhone_number_needs_agreement() {
			return phone_number_needs_agreement;
		}
		public void setPhone_number_needs_agreement(Boolean phone_number_needs_agreement) {
			this.phone_number_needs_agreement = phone_number_needs_agreement;
		}
		public String getPhone_number() {
			return phone_number;
		}
		public void setPhone_number(String phone_number) {
			this.phone_number = phone_number;
		}
		public Boolean getCi_needs_agreement() {
			return ci_needs_agreement;
		}
		public void setCi_needs_agreement(Boolean ci_needs_agreement) {
			this.ci_needs_agreement = ci_needs_agreement;
		}
		public String getCi() {
			return ci;
		}
		public void setCi(String ci) {
			this.ci = ci;
		}
		public Date getCi_authenticated_at() {
			return ci_authenticated_at;
		}
		public void setCi_authenticated_at(Date ci_authenticated_at) {
			this.ci_authenticated_at = ci_authenticated_at;
		}
		public class Profile {

            private String nickname; //닉네임
            private String thumbnail_image_url; //프로필 미리보기 이미지 URL
            private String profile_image_url; //프로필 사진 URL
            private String is_default_image; //프로필 사진 URL 기본 프로필인지 여부
            private Boolean is_default_nickname; //true : 기본 닉네임, false : 사용자 등록
            
			public String getNickname() {
				return nickname;
			}
			public void setNickname(String nickname) {
				this.nickname = nickname;
			}
			public String getThumbnail_image_url() {
				return thumbnail_image_url;
			}
			public void setThumbnail_image_url(String thumbnail_image_url) {
				this.thumbnail_image_url = thumbnail_image_url;
			}
			public String getProfile_image_url() {
				return profile_image_url;
			}
			public void setProfile_image_url(String profile_image_url) {
				this.profile_image_url = profile_image_url;
			}
			public String getIs_default_image() {
				return is_default_image;
			}
			public void setIs_default_image(String is_default_image) {
				this.is_default_image = is_default_image;
			}
			public Boolean getIs_default_nickname() {
				return is_default_nickname;
			}
			public void setIs_default_nickname(Boolean is_default_nickname) {
				this.is_default_nickname = is_default_nickname;
			}

        }
    }

    public class Partner {
      
        private String uuid; //고유 ID

		public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
    }

}