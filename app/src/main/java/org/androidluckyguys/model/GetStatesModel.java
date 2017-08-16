package org.androidluckyguys.model;

import java.util.ArrayList;

/**
 * Created by lucky on 08/06/2017.
 */

public class GetStatesModel {

    String loginId,responseId,responseCode,responseMessage,displayResponseMessage,contentData,sessionId;

    public ArrayList<MasterStateVOs> getMasterStateVOs() {
        return masterStateVOs;
    }

    public void setMasterStateVOs(ArrayList<MasterStateVOs> masterStateVOs) {
        this.masterStateVOs = masterStateVOs;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getDisplayResponseMessage() {
        return displayResponseMessage;
    }

    public void setDisplayResponseMessage(String displayResponseMessage) {
        this.displayResponseMessage = displayResponseMessage;
    }

    public String getContentData() {
        return contentData;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    ArrayList<MasterStateVOs> masterStateVOs = new ArrayList<MasterStateVOs>();
    /*{
  "loginId": null,
  "responseId": null,
  "responseCode": 0,
  "responseMessage": "Success",
  "displayResponseMessage": "Success",
  "contentData": null,
  "sessionId": "QLEjnwM+2kWG/HmbQOudsdytsxZ06I9jujPXtm9dPlqq9EUePqH964VUem/r5MH9FhksI39yhrWw4x5n3Jqa3DgRke6ZKVK1IMiZMomViFQu8hP58cbhpS70jtHh0GOOrsMcFBRWUK3B6szpNkpeIWyVPi33VgcqgkZqf19dNbM3zPpvSCaJ8DS/XKEEhvuL3MaXKyZgU0ql+BfA8A717nHBWXaPv3T5bc/Ko//yCWNoDWrZt/nltj6DbWzpljIb",
  "masterStateVOs": [
    {
      "stateId": 1,
      "stateCode": "JHR",
      "stateName": "JOHOR"
    },
    {
      "stateId": 2,
      "stateCode": "KDH",
      "stateName": "KEDAH"
    },
    {
      "stateId": 3,
      "stateCode": "KTN",
      "stateName": "KELANTAN"
    },
    {
      "stateId": 6,
      "stateCode": "MLK",
      "stateName": "MELAKA"
    },
    {
      "stateId": 7,
      "stateCode": "NSN",
      "stateName": "NEGERI SEMBILAN"
    },
    {
      "stateId": 8,
      "stateCode": "PHG",
      "stateName": "PAHANG"
    },
    {
      "stateId": 12,
      "stateCode": "PRK",
      "stateName": "PERAK"
    },
    {
      "stateId": 10,
      "stateCode": "PLS",
      "stateName": "PERLIS"
    },
    {
      "stateId": 11,
      "stateCode": "PNG",
      "stateName": "PULAU PINANG"
    },
    {
      "stateId": 13,
      "stateCode": "SBH",
      "stateName": "SABAH"
    },
    {
      "stateId": 15,
      "stateCode": "SRW",
      "stateName": "SARAWAK"
    },
    {
      "stateId": 14,
      "stateCode": "SGR",
      "stateName": "SELANGOR"
    },
    {
      "stateId": 16,
      "stateCode": "TRG",
      "stateName": "TERENGGANU"
    },
    {
      "stateId": 4,
      "stateCode": "KUL",
      "stateName": "WILAYAH PERSEKUTUAN-KUALA LUMPUR"
    },
    {
      "stateId": 5,
      "stateCode": "LBN",
      "stateName": "WILAYAH PERSEKUTUAN-LABUAN"
    },
    {
      "stateId": 9,
      "stateCode": "PJY",
      "stateName": "WILAYAH PERSEKUTUAN-PUTRAJAYA"
    }
  ]
}*/
}
