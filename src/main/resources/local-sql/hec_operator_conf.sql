-- FINANCE_MAINT.HEC_OPERATOR_CONF definition

CREATE TABLE "HEC_OPERATOR_CONF" 
   (	"ID" NUMBER(38,0) NOT NULL ENABLE, 
	"PROCESS_DEFINITION_ID" VARCHAR2(200) NOT NULL ENABLE, 
	"PROCESS_DEFINITION_KEY" VARCHAR2(200) NOT NULL ENABLE, 
	"PROCESS_DEFINITION_VERSION" VARCHAR2(100) NOT NULL ENABLE, 
	"OPERATOR_PARAM_KEY" VARCHAR2(200) NOT NULL ENABLE, 
	"OPERATOR_SOURCE" VARCHAR2(200) NOT NULL ENABLE, 
	"OPERATOR_NULLABLE_STRATEGY" VARCHAR2(200) NOT NULL ENABLE, 
	"PERMIT_TYPE" VARCHAR2(200), 
	"CANDIDATE_OPERATOR" VARCHAR2(100), 
	"CANDIDATE_ORG_NODE_CODE" VARCHAR2(100), 
	"CANDIDATE_ROLE_CODE" VARCHAR2(100), 
	"CREATE_TIME" TIMESTAMP (6) DEFAULT sysdate NOT NULL ENABLE, 
	"UPDATE_TIME" TIMESTAMP (6) DEFAULT sysdate NOT NULL ENABLE, 
	"PROCESS_DEFINITION_TASK_ID" VARCHAR2(100) NOT NULL ENABLE, 
	"OPERATOR_COLLECTION_PARAM_KEY" VARCHAR2(200), 
	"RENDER_CANDIDATE_OPERATOR" VARCHAR2(500)
   ) ;

COMMENT ON COLUMN "HEC_OPERATOR_CONF"."ID" IS 'id';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."PROCESS_DEFINITION_ID" IS '流程定义id';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."PROCESS_DEFINITION_KEY" IS '审批流key';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."PROCESS_DEFINITION_VERSION" IS '审批版本';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."OPERATOR_PARAM_KEY" IS '执行人参数id';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."OPERATOR_SOURCE" IS '执行人来源';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."OPERATOR_NULLABLE_STRATEGY" IS '执行人为空时执行策略';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."PERMIT_TYPE" IS '审批方式';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."CANDIDATE_OPERATOR" IS '执行人userId候选值，英文逗号分隔';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."CANDIDATE_ORG_NODE_CODE" IS '架构节点候选值code,英文逗号分隔';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."CANDIDATE_ROLE_CODE" IS '候选角色节点code,英文逗号分隔';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."CREATE_TIME" IS '创建时间';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."UPDATE_TIME" IS '更新时间';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."PROCESS_DEFINITION_TASK_ID" IS '流程执行人所在节点id';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."OPERATOR_COLLECTION_PARAM_KEY" IS '执行人集合参数key';
 
   COMMENT ON COLUMN "HEC_OPERATOR_CONF"."RENDER_CANDIDATE_OPERATOR" IS '候选执行人组织架构渲染字段（无实际意义，前端使用）';