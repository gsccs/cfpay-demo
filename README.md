# cfpay-demo
鉴权接口
为消费信贷及小微贷款提供风控服务，为用户提供从身份验证、反欺诈到信用评分的（贷前）全流程风控服务。
典型用例：用户输入被查询人姓名、身份证、手机号、银行卡号四要素，产品返回风险评估报告和决策建议。
客户群体：      信贷机构、助贷机构、数据公司等企业用户
目标业务类型：现金贷、消费分期、信用卡余额代偿等
场景：             线上、线下
1.   两要素认证，卡号 +姓名
2.   两要素认证，卡号 +身份证号
3.   三要素认证，卡号 + 姓名 +身份证号
4.   四要素认证，卡号 +姓名 +身份证号 +手机号
5.  六要素认证，卡号 +姓名 +身份证号 +手机号+CVN2+有效期
6.  两要素认证，姓名 +身份证号(实名认证)
7.  两要素认证返回照片，姓名 +身份证号(实名认证)
8. 运营商两要素认证，姓名 +手机号
9. 运营商三要素认证，姓名 +手机号 + 身份证号
10. 火眼查询，即持卡人的黑名单查询
11. 乘机记录认证，姓名 + 身份证号
12. 高端商旅认证，姓名 + 身份证号
13. 航空出行认证，姓名 + 身份证号
14. 跨境出行认证，姓名 + 护照号
15. 证券开户信息认证，姓名 + 身份证号 + 证券机构号
16. 工商信息认证：企业名称
17. 企业4要素认证：企业名称 + 企业信用代码 + 法人姓名 + 法人身份证号
18. 身份证图像认证：正面图片 + 背面图片（照片大小最好不要超过50K）
19. 低清人像认证：姓名 + 身份证号 + 图片（大小不要超过50K）
20. 高清人像认证：姓名 + 身份证号 + 图片（大小不要超过50K）
21. 学历认证：企业名称 + 企业信用代码 + 法人姓名 + 法人身份证号
