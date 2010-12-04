MittieWiki (miki) is a single-user personal wiki,
in fact a port of my personal (ruby) wiki that I have used for 8 years now.

The new system uses the Grails platform, mainly for the sake of easier extensibility
and readability of the code.

This is a spare-time pet project, which has no roadmap and no promised features and milestones whatsoever.
Feel to use it to your liking but do *not expect any support* from my side.

General concept
---------------
All wiki pages are stored on disk, in a single flat directory that
is configured in the Grails config under the key miki.pages.dir. with the
extension '.wiki'.

These pages can be created, deleted, searched, backuped, and edited as "raw" content by the usual means
of a texteditor or through the miki webapp.

The miki is a locally running webapp that presents these pages in the "cooked" format, i.e. HTML generated from
the raw miki syntax.

How the syntax exactly looks like, how it can be extended, and what further
capabilities the miki has, will be a question of discovery.


Have at it
—Dierk (Mittie) König