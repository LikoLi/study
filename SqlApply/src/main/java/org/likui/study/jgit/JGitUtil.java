package org.likui.study.jgit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JGitUtil {
    public static void clone(String url, File localPath) throws GitAPIException {
        Git call = Git.cloneRepository().setURI(url).setDirectory(localPath).call();
        System.out.println(call.getRepository().getDirectory());
    }

    public static void main(String[] args) throws GitAPIException, IOException {
        File localPath = new File("E:\\学习整理\\JGit\\jgit-cookbook");
//        clone("https://github.com/LikoLi/tools.git", localPath);
        Git open = Git.open(localPath);
        List<Ref> call1 = open.branchList().call();
        for (Ref ref : call1) {
            System.out.println(ref.getName());
        }
        Iterable<RevCommit> call = open.log().call();
        for (RevCommit commit : call) {
            String fullMessage = commit.getFullMessage();
            System.out.println(fullMessage);
        }
    }
}
