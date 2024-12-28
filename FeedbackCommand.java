public class FeedbackCommand implements Command {
    private Feedback feedback;

    public FeedbackCommand(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public void execute() {
        feedback.getFeedback();
        feedback.displayFeedback();
    }
}
