package com.livescorescrickets.livescores;

import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchResultModel;
import com.livescorescrickets.livescores.Pojo.MultiMatch.GetUpcomingMatchesPojo;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

public interface OnItemClickListener {
    void setOnItemClickListener(int i, MatchResultModel.AllMatch allMatch);

    void setOnItemClickListener(int i, GetUpcomingMatchesPojo.AllMatch allMatch);

    void setOnItemClickListener(int i, MultimatchPojo multimatchPojo);
}
