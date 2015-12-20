package com.khmelenko.lab.travisclient.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khmelenko.lab.travisclient.R;
import com.khmelenko.lab.travisclient.converter.BuildStateHelper;
import com.khmelenko.lab.travisclient.converter.TimeConverter;
import com.khmelenko.lab.travisclient.network.response.Branch;
import com.khmelenko.lab.travisclient.network.response.Build;
import com.khmelenko.lab.travisclient.network.response.Commit;
import com.khmelenko.lab.travisclient.util.DateTimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * View with build info
 *
 * @author Dmytro Khmelenko
 */
public class BuildView extends LinearLayout {

    @Bind(R.id.build_number)
    TextView mNumber;

    @Bind(R.id.build_state)
    TextView mState;

    @Bind(R.id.build_branch)
    TextView mBranch;

    @Bind(R.id.build_commit_message)
    TextView mCommitMessage;

    @Bind(R.id.build_commit_person)
    TextView mCommitPerson;

    @Bind(R.id.build_duration)
    TextView mDuration;

    @Bind(R.id.build_finished)
    TextView mFinished;

    public BuildView(Context context) {
        super(context);
        initializeViews(context);
    }

    public BuildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public BuildView(Context context,
                     AttributeSet attrs,
                     int defStyle) {
        super(context, attrs, defStyle);
        initializeViews(context);
    }

    /**
     * Inflates the views in the layout.
     *
     * @param context the current context for the view.
     */
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_build, this);
        ButterKnife.bind(this, view);
    }

    /**
     * Sets build data
     *
     * @param build  Build
     * @param commit Related commit
     */
    public void setBuildData(Build build, Commit commit) {
        // build data
        mNumber.setText(getContext().getString(R.string.build_build_number, build.getNumber()));
        String state = build.getState();
        if (!TextUtils.isEmpty(state)) {
            int buildColor = BuildStateHelper.getBuildColor(state);
            mState.setText(state);
            mState.setTextColor(buildColor);
            mNumber.setTextColor(buildColor);

            Drawable drawable = BuildStateHelper.getBuildImage(state);
            if (drawable != null) {
                drawable.setBounds(0, 0, 30, 30);
                mNumber.setCompoundDrawables(drawable, null, null, null);
            }
        }

        // commit data
        if (commit != null) {
            mBranch.setText(commit.getBranch());
            mCommitMessage.setText(commit.getMessage());
            mCommitPerson.setText(commit.getCommitterName());
        }

        // finished at
        if (!TextUtils.isEmpty(build.getFinishedAt())) {
            String formattedDate = DateTimeUtils.parseAndFormatDateTime(build.getFinishedAt());
            formattedDate = getContext().getString(R.string.build_finished_at, formattedDate);
            mFinished.setText(formattedDate);
        } else {
            String stateInProgress = getContext().getString(R.string.build_build_in_progress);
            String finished = getContext().getString(R.string.build_finished_at, stateInProgress);
            mFinished.setText(finished);
        }

        // duration
        if (build.getDuration() != 0) {
            String duration = TimeConverter.durationToString(build.getDuration());
            duration = getContext().getString(R.string.build_duration, duration);
            mDuration.setText(duration);
        } else {
            String stateInProgress = getContext().getString(R.string.build_build_in_progress);
            String duration = getContext().getString(R.string.build_duration, stateInProgress);
            mDuration.setText(duration);
        }
    }

    public void setBranchData(Branch branch, Commit commit) {
        // build data
        mNumber.setText(getContext().getString(R.string.build_build_number, branch.getNumber()));
        String state = branch.getState();
        if (!TextUtils.isEmpty(state)) {
            int buildColor = BuildStateHelper.getBuildColor(state);
            mState.setText(state);
            mState.setTextColor(buildColor);
            mNumber.setTextColor(buildColor);

            Drawable drawable = BuildStateHelper.getBuildImage(state);
            if (drawable != null) {
                drawable.setBounds(0, 0, 30, 30);
                mNumber.setCompoundDrawables(drawable, null, null, null);
            }
        }

        // commit data
        if (commit != null) {
            mBranch.setText(commit.getBranch());
            mCommitMessage.setText(commit.getMessage());
            mCommitPerson.setText(commit.getCommitterName());
        }

        // finished at
        if (!TextUtils.isEmpty(branch.getFinishedAt())) {
            String formattedDate = DateTimeUtils.parseAndFormatDateTime(branch.getFinishedAt());
            formattedDate = getContext().getString(R.string.build_finished_at, formattedDate);
            mFinished.setText(formattedDate);
        } else {
            String stateInProgress = getContext().getString(R.string.build_build_in_progress);
            String finished = getContext().getString(R.string.build_finished_at, stateInProgress);
            mFinished.setText(finished);
        }

        // duration
        if (branch.getDuration() != 0) {
            String duration = TimeConverter.durationToString(branch.getDuration());
            duration = getContext().getString(R.string.build_duration, duration);
            mDuration.setText(duration);
        } else {
            String stateInProgress = getContext().getString(R.string.build_build_in_progress);
            String duration = getContext().getString(R.string.build_duration, stateInProgress);
            mDuration.setText(duration);
        }
    }
}