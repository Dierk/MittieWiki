MittieWiki (miki) is a single-user personal wiki,
in fact a port of my personal (ruby) wiki that I have used for 8 years now.

The new system uses the Grails platform, mainly for the sake of easier extensibility
and readability of the code. It also is __an exercise in simplicity__.

This is a spare-time pet project, which has no roadmap and no promised features and milestones whatsoever.
Feel free to use it to your liking but do __not expect any support__ from my side.

General concept
===============
All wiki pages are stored on disk, in a single flat directory that
is configured in the Grails config under the key miki.pages.dir. with the
extension '.wiki'.

These pages can be created, deleted, searched, backuped, and edited as "raw" content by the usual means
of a texteditor or through the miki webapp.

The miki is a locally running webapp that presents these pages in the "cooked" format, i.e. HTML generated from
the raw miki syntax.

How the syntax exactly looks like, how it can be extended, and what further
capabilities the miki has, will be a question of discovery.  

Yes, I know
-----------
that my implementation is ridiculously ineffecient and that I could use
caching instead of reading the file system all the time.

The point is that even with the content of 8 years that I already have, the
solution is fast enough and I don't need optimization, yet. 

Quality
-------
The system measures its test coverage using cobertura. Testing is done
with Groovy, Grails, JUnit, Spock, and Canoo WebTest.

As of now, it has 100% line coverage, 100% branch coverage, and 
a surprising complexity of 0.

Cobertura counts 73 lines of production code.
Not much for an application that I use every day to manage many aspects of
my professional and private life.

To run the analysis, use 'grails test-app -coverage' and have a look at
the target/test-reports folder.

Have at it
—-Dierk (Mittie) König